<template>
  <q-page>
    <input-component @clicked="onClicked" />
    <show-component :payments="payments" />
  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import InputComponent from 'components/InputComponent.vue'
import ShowComponent from 'components/ShowComponent.vue'
import { Payment } from '../model/payment'

@Component({
  components: {
    InputComponent,
    ShowComponent
  }
})
export default class InputPage extends Vue {
  private payments: Payment[] = []

  onClicked (val: any): void {
    if (val === 'done') {
      this.onDone()
    } else {
      this.payments.push(val)
    }
  }

  onDone (): void {
    this.redirect('/result')
  }

  redirect (path: string): void {
    this.$router.push({ path: path })
  }
}
</script>
