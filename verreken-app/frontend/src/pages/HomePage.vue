<template>
  <q-page>
    <input-component @clicked="onClicked" />
    <q-separator />
    <stake-component :payments="payments" />
    <q-separator />
    <settlement-component :settlement="settlement" />
  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import InputComponent from 'components/InputComponent.vue'
import StakeComponent from 'components/StakeComponent.vue'
import SettlementComponent from 'components/SettlementComponent.vue'
import WebSocketService from '../services/web.socket.service'
import HttpService from '../services/http.service'
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
  private httpService: HttpService
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
    this.httpService = new HttpService()
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
    if (this.settlement.owes.length > 0) {
      this.httpService.postPayments(this.payments).then((response) => {
        console.log(response)
      }).catch((error) => {
        console.log(error)
      })

      this.payments = []
      this.settlement = <Settlement>{}
    }
  }

  onRemove (): void {
    this.payments.shift()
    this.send(JSON.stringify(this.payments))
  }

  onHistory (): void {
    if (this.payments.length === 0) {
      this.redirect('/history')
    }
  }

  onNewPayment (payment: Payment): void {
    this.payments.unshift(payment)
    this.send(JSON.stringify(this.payments))
  }
}
</script>
