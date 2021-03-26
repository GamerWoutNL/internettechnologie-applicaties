<template>
  <q-page>
    <input-component @clicked="onClicked" />
    <stake-component :payments="payments" />
    <settlement-component :settlement="settlement" />
  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import InputComponent from 'components/InputComponent.vue'
import StakeComponent from 'components/StakeComponent.vue'
import SettlementComponent from 'components/SettlementComponent.vue'
import WebSocketService from '../services/web.socket.service'
import { Payment } from '../model/payment'
import { Settlement } from '../model/settlement'

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
  private settlement: Settlement = <Settlement>{}

  send (message: string): void {
    this.webSocketService.send(message)
  }

  onMessage (message: string): void {
    this.settlement = JSON.parse(message)
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
    if (this.payments.length > 1) {
      // request history
      this.payments = []
    }
  }

  onRemove (): void {
    this.payments.pop()

    if (this.payments.length > 1) {
      this.send(JSON.stringify(this.payments))
    }
  }

  onHistory (): void {
    this.redirect('/history')
  }

  onNewPayment (payment: Payment): void {
    this.payments.unshift(payment)

    if (this.payments.length > 1) {
      this.send(JSON.stringify(this.payments))
    }
  }
}
</script>
