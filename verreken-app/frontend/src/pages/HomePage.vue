<template>
  <q-page>
    <input-component @clicked="onClicked" />
    <stake-component :payments="payments" />
    <settlement-component />
  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import InputComponent from 'components/InputComponent.vue'
import StakeComponent from 'components/StakeComponent.vue'
import SettlementComponent from 'components/SettlementComponent.vue'
import WebSocketService from '../services/web.socket.service'
import { Payment } from '../model/payment'

@Component({
  components: {
    InputComponent,
    StakeComponent,
    SettlementComponent
  }
})
export default class HomePage extends Vue {
  private webSocketService: WebSocketService
  private payments: Payment[] = []

  send (message: string): void {
    this.webSocketService.send(message)
  }

  onMessage (message: string): void {
    console.log(message)
  }

  redirect (path: string): void {
    this.$router.push({ path: path }).catch((error) => {
      console.log(error)
    })
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

  onClicked (val: any): void {
    switch (val) {
      case 'done': {
        this.onDone()
        break
      }
      case 'remove': {
        this.onRemove()
        break
      }
      case 'history': {
        this.onHistory()
        break
      }
      default: {
        this.onNewPayment(val)
        break
      }
    }
  }

  onDone (): void {
    if (this.payments.length > 0) {
      this.send(JSON.stringify(this.payments))
      this.payments = []
    }
  }

  onRemove (): void {
    this.payments.pop()
  }

  onHistory (): void {
    this.redirect('/history')
  }

  onNewPayment (payment: Payment): void {
    this.payments.push(payment)
  }
}
</script>
