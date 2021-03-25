<template>
  <q-page>
    <input-component @add="onAdd"></input-component>
  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import InputComponent from 'components/InputComponent.vue'
import WebSocketService from '../services/web.socket.service'

@Component({
  components: {
    InputComponent
  }
})
export default class HomePage extends Vue {
  private webSocketService: WebSocketService

  send (message: string): void {
    this.webSocketService.send(message)
  }

  onAdd (value: string): void {
    console.log(value)
  }

  onMessage (message: string): void {
    console.log(message)
  }

  mounted () {
    this.webSocketService = new WebSocketService()
    this.webSocketService.connect((message) => {
      this.onMessage(message)
    })
  }

  beforeDestroy () {
    this.webSocketService.disconnect()
  }
}
</script>
