<template>
  <div>
    <div class="font-medium margin" style="font-size: 5vw">Verrekeningen</div>
    <q-scroll-area style="height: 20vh; max-width: 100vw;">
      <div class="margin font-light" v-for="owe in settlement.owes" :key="owe.id">
          {{owe.from}} betaalt {{owe.to}} €{{owe.amount.toFixed(2)}}
      </div>
    </q-scroll-area>
  </div>
</template>

<script lang="ts">
import { Settlement } from '../model/settlement'
import { Vue, Prop, Component } from 'vue-property-decorator'

@Component({
  components: { }
})
export default class SettlementComponent extends Vue {
  @Prop() readonly settlement: Settlement
}
</script>

<style lang="scss">
.margin {
  margin: 3vw;
}
</style>
