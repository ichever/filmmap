import Vue from 'vue'
import App from '@/App'

describe('App.vue', () => {
  it('should render input field with placeholder', () => {
    const Constructor = Vue.extend(App)
    const vm = new Constructor().$mount()
    console.log(vm.$el)
    expect(vm.$el.querySelector('input').getAttribute('placeholder')).to.equal('Enter movie name')
  })
})
