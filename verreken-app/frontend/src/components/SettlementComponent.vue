<template>
  <div>
    <q-separator />
    <div class="font-medium margin" style="font-size: 5vw">Verrekeningen</div>
    <q-scroll-area style="height: 20vh; max-width: 100vw;">
      <div class="margin font-light" v-for="n in 100" :key="n">
          {{n}}
      </div>
    </q-scroll-area>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'

@Component({
  components: { }
})
export default class SettlementComponent extends Vue {
}
</script>

<style lang="scss">
.margin {
  margin: 3vw;
}
</style>
