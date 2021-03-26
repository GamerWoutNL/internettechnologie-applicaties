<template>
  <q-page>
    <q-btn class="margin" icon="keyboard_return" size="5vw" round color="primary" @click="onBack" />
    <q-scroll-area style="height: 80vh; max-width: 100vw;">
      <div class="font-light margin" v-for="settlement in settlements" :key="settlement.id">
        {{settlement.date}}
        <div class="font-light margin" v-for="owe in settlement.owes" :key="owe.id">
          {{owe.from}} betaalt {{owe.to}} €{{owe.amount.toFixed(2)}}
        </div>
        <q-separator />
      </div>
    </q-scroll-area>
  </q-page>
</template>

<script lang="ts">
import { Settlement } from 'src/model/settlement'
import { Vue, Component } from 'vue-property-decorator'
import HttpService from '../services/http.service'

@Component({
  components: { }
})
export default class HistoryPage extends Vue {
  private httpService: HttpService
  private settlements: Settlement[] = []

  onBack (): void {
    this.redirect('/')
  }

  redirect (path: string): void {
    this.$router.push({ path: path }).catch((error) => {
      console.log(error)
    })
  }

  mounted () {
    this.httpService = new HttpService()
    this.httpService.getHistory().then((response) => {
      console.log(response.data)
      this.settlements = response.data as Settlement[]
    }).catch((error) => {
      console.log(error)
    })
  }
}
</script>

<style lang="scss">
.margin {
  margin: 3vw;
}
</style>
