<template>
  <q-page>
    <input-component @clicked="onClicked" />
    <show-component />
  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import InputComponent from 'components/InputComponent.vue'
import ShowComponent from 'components/ShowComponent.vue'
import WebSocketService from '../services/web.socket.service'
import { User } from '../model/user'

@Component({
  components: {
    InputComponent,
    ShowComponent
  }
})
export default class InputPage extends Vue {
  private webSocketService: WebSocketService
  private users: User[] = []

  send (message: string): void {
    this.webSocketService.send(message)
  }

  onClicked (val: any): void {
    if (val === 'done') {
      this.onDone()
    } else {
      this.users.push(val)
    }
  }

  onDone (): void {
    console.log('were done boys')
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
