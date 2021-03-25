<template>
  <div>
    <q-input class="margin" rounded outlined v-model="name" label="Naam">
      <template v-if="!isNameCorrect" v-slot:append>
        <q-icon name="priority_high" color="red" />
      </template>
    </q-input>
    <q-input class="margin" rounded outlined v-model="payment" label="Inleg">
      <template v-if="!isPaymentCorrect" v-slot:append>
        <q-icon name="priority_high" color="red" />
      </template>
    </q-input>
    <q-btn class="margin" icon="add" size="5vw" round color="primary" @click="add" />
    <q-btn class="margin" icon="remove" size="5vw" round color="primary" @click="again" />
    <q-btn class="margin" icon="done" size="5vw" round color="primary" @click="done" />
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

      this.$emit('clicked', user)
      this.reset()
    }
  }

  again (): void {
    this.reset()
  }

  done (): void {
    this.$emit('clicked', 'done')
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

<style lang="scss">
.margin {
  margin: 3vw;
}
</style>
