<template>
  <q-page>
    <q-input rounded standout v-model="text" />
    <q-btn @click="send()">Add</q-btn>
    <q-btn @click="again()">Again</q-btn>
  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import { WebSocketService } from '../services/web.socket.service'

@Component({
  components: { }
})
export default class MainLayout extends Vue {
  webSocketService: WebSocketService
  content = ''
  text = ''

  send (): void {
    this.webSocketService.send(this.text)
    this.text = ''
  }

  again (): void {
    this.text = ''
  }

  mounted () {
    this.webSocketService = new WebSocketService()
    this.webSocketService.connect((message) => {
      this.content = message
    })
  }

  beforeDestroy () {
    this.webSocketService.disconnect()
  }
}
</script>
