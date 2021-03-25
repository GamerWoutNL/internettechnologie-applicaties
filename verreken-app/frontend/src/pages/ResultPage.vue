<template>
  <q-page>
    <q-spinner-orbit v-if="loading" color="primary" size="2em" />
    <div v-if="!loading">RESULTS</div>
  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import WebSocketService from '../services/web.socket.service'

@Component({
  components: { }
})
export default class InputPage extends Vue {
  private webSocketService: WebSocketService
  loading = true

  send (message: string): void {
    this.webSocketService.send(message)
  }

  onMessage (message: string): void {
    console.log(message)
  }

  redirect (path: string): void {
    this.$router.push({ path: path })
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
