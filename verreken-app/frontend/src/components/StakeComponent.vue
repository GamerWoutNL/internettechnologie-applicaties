<template>
  <div>
    <div class="margin font-light title" style="font-size: 5vw">Inleg</div>
    <q-separator class="seperator-margin" />
    <q-scroll-area style="height: 15vh; max-width: 100vw;">
      <div class="margin font-light" v-for="payment in payments" :key="payment.id">
        {{payment.name}} heeft €{{payment.amount}} ingelegd voor '{{payment.description}}'
      </div>
    </q-scroll-area>
    <div class="margin font-light" v-if="this.totalPayments > 0">In totaal is er €{{totalPayments.toFixed(2)}} verbrast!</div>
    <div class="margin font-light" v-else>Er is helaas nog niks verbrast</div>
  </div>
</template>

<script lang="ts">
import { Vue, Prop, Watch, Component } from 'vue-property-decorator'
import { Payment } from '../model/payment'

@Component({
  components: { }
})
export default class StakeComponent extends Vue {
  @Prop() readonly payments: Payment[]

  private totalPayments = 0

  mounted () {
    this.refreshPayments()
  }

  refreshPayments (): void {
    this.totalPayments = 0
    this.payments.forEach((payment) => {
      this.totalPayments += parseFloat(payment.amount)
    })
  }

  @Watch('payments')
  onPaymentsChanged () {
    this.refreshPayments()
  }
}
</script>

<style lang="scss" scoped>
.margin {
  margin: 3vw;
}
.title {
  text-align: center;
}
</style>
