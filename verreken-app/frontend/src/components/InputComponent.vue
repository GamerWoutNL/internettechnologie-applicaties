<template>
  <div>
    <q-input rounded standout v-model="name" label="Naam">
      <template v-if="!isNameCorrect" v-slot:append>
        <q-icon name="priority_high" color="red" />
      </template>
    </q-input>
    <q-input rounded standout v-model="payment" label="Inleg">
      <template v-if="!isPaymentCorrect" v-slot:append>
        <q-icon name="priority_high" color="red" />
      </template>
    </q-input>
    <q-btn @click="add">Toevoegen</q-btn>
    <q-btn @click="again">Opnieuw</q-btn>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import { User } from '../model/user'

@Component({
  components: { }
})
export default class InputComponent extends Vue {
  private name = ''
  private payment = ''
  private isNameCorrect = true
  private isPaymentCorrect = true

  add (): void {
    this.payment = this.payment.replace(/,/g, '.')

    this.isNameCorrect = this.isName(this.name)
    this.isPaymentCorrect = this.isNumber(this.payment)

    if (this.isNameCorrect && this.isPaymentCorrect) {
      const user: User = {
        name: this.name,
        payment: parseFloat(this.payment).toFixed(2)
      }

      this.$emit('add', user)
      this.reset()
    }
  }

  again (): void {
    this.reset()
  }

  reset (): void {
    this.name = ''
    this.payment = ''
    this.isNameCorrect = true
    this.isPaymentCorrect = true
  }

  isName (n: any): boolean {
    return /^[a-zA-Z]+$/.test(n)
  }

  isNumber (n: any): boolean {
    return /^-?[\d.]+(?:e-?\d+)?$/.test(n)
  }
}
</script>
