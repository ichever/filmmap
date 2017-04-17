<template>
	<div id='main'>

		<div id='film-info-search'>

			<div class="search-field">
				<i class='fa fa-spinner fa-spin' v-if='loading'></i>
				<template v-else>
					<i class='fa fa-search' v-show='isEmpty'></i>
					<i class='fa fa-times' v-show="isDirty" @click="reset"></i>
				</template>


				<!-- the input field -->
				<input type='text' placeholder='Enter movie name'
					   autocomplete='off' v-model='query'
					   @keydown.down='down'
					   @keydown.up='up'
					   @keydown.enter='hit'
					   @keydown.esc='reset'
					   @blur='reset'
					   @input='update'/>
			</div>
			<!-- the list -->
			<ul v-show='hasItems'>
				<li v-for='(item, $item) in items' :class='activeClass($item)' @mousedown='hit'
					@mousemove='setActive($item)'>
					<span v-text='item.name'></span>
				</li>
			</ul>

			<div id='film-detail'>
				<div class='film-item'>
					<div id='film-title' class=''>{{film.title}}</div>
					<div id='film-release-year' class='item-value'>({{film.releaseYear}})</div>
				</div>

				<div class='film-item' v-if="film.distributor != ''">
					<div class='item-label font-small'>Distributor:&nbsp;</div>
					<div class='item-value font-small'>{{film.distributor}}</div>
				</div>

				<div class='film-item' v-if="film.writer != ''">
					<div class='item-label font-small'>Writer:&nbsp;</div>
					<div class='item-value font-small'>{{film.writer}}</div>
				</div>

				<div class='film-item' v-if="hasActors">
					<div class='item-label font-small'>Actors:&nbsp;</div>
					<div class='item-value font-small' v-for="actor in film.actors">
						<div class='item-actor'>{{actor.name}},&nbsp;</div>
					</div>
				</div>

				<div id='film-fun-facts' class='film-item' v-if="film.funFacts != ''">
					<div class=' font-small'>{{film.funFacts}}</div>
				</div>

			</div>
		</div>

		<div id='map-detail'>
			<gmap-map :center='center' :zoom='12' style='width: 100%; height: 870px'>
				<gmap-marker
						v-for='m in markers'
						:position='m.position'
						:clickable='true'
						:draggable='true'
						@click='center=m.position'
				></gmap-marker>
			</gmap-map>
		</div>

	</div>
</template>

<script>

  import * as VueGoogleMaps from 'vue2-google-maps'
  import axios from 'axios'
  import Vue from 'vue'
  import VueTypeahead from 'vue-typeahead'

  Vue.use(VueGoogleMaps, {
    load: {
      key: 'AIzaSyBNCLmXai1-W7259Uj2QWHWp1mXPbl-GpI'
    }
  })

  export default {
    extends: VueTypeahead,

    name: 'app',

    data: function () {
      return {

        query: '',

        src: 'http://localhost:8080/films/like',

        data: '',

        limit: 10,

        minChars: 1,

        selectFirst: true,

        queryParamName: 'title',

        film: {
          title: 'Looking Season 2 ep 204',
          releaseYear: 2015,
          funFacts: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
          productionCompany: 'Mission Street Productions, LLC',
          distributor: 'HBO',
          director: 'Andrew Haigh',
          writer: 'Michael Lannan',
          locations: [
            {
              id: 18,
              name: '534 Hyde Street',
              latitude: 37.78597,
              longitude: -122.41611
            },
            {
              id: 24,
              name: '970 Geary Street',
              latitude: 37.786137,
              longitude: -122.4193
            },
            {
              id: 17,
              name: '3650 21st Street',
              latitude: 37.756535,
              longitude: -122.42909
            },
            {
              id: 22,
              name: 'Larken Street Youth Services, 1138 Sutter Street',
              latitude: 37.78226,
              longitude: -122.41302
            },
            {
              id: 16,
              name: 'Castro Street @ 18th street',
              latitude: 37.76089,
              longitude: -122.43503
            },
            {
              id: 19,
              name: 'Urban Flowers, 4029 18th Street',
              latitude: 37.760887,
              longitude: -122.433395
            },
            {
              id: 15,
              name: 'Daniels Pharmacy, 943 Geneva Ave.',
              latitude: 37.71632,
              longitude: -122.440346
            },
            {
              id: 25,
              name: '601 Buena Vista Ave West',
              latitude: 37.766922,
              longitude: -122.44403
            },
            {
              id: 26,
              name: 'Dolores Park, San Francisco',
              latitude: 37.759617,
              longitude: -122.4269
            },
            {
              id: 21,
              name: '1890 Clay Street',
              latitude: 37.792194,
              longitude: -122.42403
            },
            {
              id: 20,
              name: 'Bi-Rite Creamery, 3692 18th Stret',
              latitude: 37.76159,
              longitude: -122.42572
            },
            {
              id: 23,
              name: 'Esta Noche, 3079 16th Street',
              latitude: 37.764824,
              longitude: -122.421326
            }
          ],
          actors: [
            {
              id: 22,
              name: 'Jonathan Groff'
            },
            {
              id: 23,
              name: 'Murray Bartlett'
            },
            {
              id: 21,
              name: 'Frankie Alvarez'
            }
          ]
        },

        center: {lat: 37.7749295, lng: -122.4194155},

        markers: [{
          position: {lat: 37.792427, lng: -122.41061}
        }, {
          position: {lat: 37.81722, lng: -122.370705}
        }]
      }
    },

    components: {
      'googleMap': Map
    },

    computed: {
      hasActors: function () {
        return this.film.actors.length > 0
      }
    },

    methods: {

      convertToPosition (locations) {
        var markers = []
        locations.forEach(function (value) {
          markers.push({
            position: {lat: value.latitude, lng: value.longitude}
          })
        })
        return markers
      },

      onHit (item) {
        this.items = []
        axios.get(`http://localhost:8080/film?id=` + item.id)
        .then(response => {
          this.film = response.data
          this.markers = this.convertToPosition(response.data.locations)
        })
        .catch(e => {
          this.errors.push(e)
        })
      },

      // The callback function which is triggered when the response data are received
      // (optional)
      prepareResponseData (data) {
        return data
      }
    }
  }
</script>

<style>
	#film-map-body {
		background-color: #e5e5e5;
		margin: 0;
	}

	#top-ban {
		height: 50px;
		background-color: #2c3e50;
		color: whitesmoke;
	}

	#top-ban-label {
		font-size: 2em;
		float: left;
		margin-left: 10px;
		margin-top: 7px;
	}

	#top-ban-sub {
		padding-top: 20px;
		padding-left: 20px;
	}

	.search-field {
		padding-left: 10px;
		padding-right: 10px;
	}

	#film-info-search {
		width: 26%;
		float: left;
		min-height: calc(100vh - 50px);
		background-color: #3f5c72;
		color: #eeeeee;
	}

	#film-detail {
		margin-top: 20px;
	}

	#film-release-year {
		margin-top: 2px;
	}

	#film-detail .film-item {
		width: 90%;
		padding-top: 10px;
		margin-left: 15px;
		clear: both;
	}

	#film-detail .item-label {
		float: left;
	}

	#film-detail .item-value {
		float: left;
		color: #ff9632;
	}

	.font-small {
		font-size: 15px;
	}

	#film-title {
		font-size: 18px;
		float: left;
		color: #f0f0f0;
		font-weight: 600;
	}

	#film-release-year {
		margin-left: 10px;
		color: #50799b;
	}

	#film-fun-facts {
		padding-top: 30px;
	}

	#main {
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		color: #2c3e50;
	}

	#map-detail {
		width: 74%;
		height: 100%;
		float: right;
		background-color: #AAB2BE;
	}

	input {
		width: 100%;
		font-size: 14px;
		color: #2c3e50;
		box-shadow: inset 0 1px 4px rgba(0, 0, 0, .4);
		-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
		transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
		font-weight: 300;
		padding: 10px 20px;
		border: none;
		border-radius: 20px;
		letter-spacing: 1px;
		box-sizing: border-box;
		font-family: "Helvetica Neue", Helvetica, sans-serif;
	}

	input:focus {
		border-color: #8ad2ff;
		outline: 0;
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px #8ad2ff;
	}

	.fa-times {
		cursor: pointer;
	}

	i.fa {
		float: right;
		position: relative;
		top: 27px;
		right: 20px;
	}

	ul {
		width: 18%;
		position: absolute;
		padding: 0;
		margin-top: 8px;
		min-width: 15%;
		background-color: #fff;
		list-style: none;
		border-radius: 4px;
		box-shadow: 0 0 10px rgba(0, 0, 0, 0.25);
		z-index: 1000;
	}

	li {
		padding: 10px 16px;
		border-bottom: 1px solid #ccc;
		cursor: pointer;
	}

	li:first-child {
		border-top-left-radius: 4px;
		border-top-right-radius: 4px;
	}

	li:last-child {
		border-bottom-left-radius: 4px;
		border-bottom-right-radius: 4px;
		border-bottom: 0;
	}

	span {
		display: block;
		color: #2c3e50;
	}

	.active {
		background-color: #8ad2ff;
	}

	.active span {
		color: white;
	}

</style>
