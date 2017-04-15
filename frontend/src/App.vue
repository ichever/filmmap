<template>
	<div id="app">
		<img src="./assets/logo.png">
		<div id="film">
			{{location}}

		</div>
		<button id="searchBtn" v-on:click="search">Search</button>
	</div>
</template>

<script>
  import axios from 'axios'
  export default {

    name: 'app',

    data: function () {
      return {
        location: 'grimstad'
      }
    },

    methods: {
      search: function () {
        axios.get(`http://localhost:8080/location?id=1`)
            .then(response => {
              // JSON responses are automatically parsed.
              this.location = response
            })
            .catch(e => {
              this.errors.push(e)
            })
      }
    }
  }
</script>

<style>
	#app {
		font-family: 'Avenir', Helvetica, Arial, sans-serif;
		-webkit-font-smoothing: antialiased;
		-moz-osx-font-smoothing: grayscale;
		text-align: center;
		color: #2c3e50;
		margin-top: 60px;
	}
</style>
