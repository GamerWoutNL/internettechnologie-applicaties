<template>
  <div>
    <q-input class="margin font-light" outlined v-model="name" label="Naam" :rules="[ val => isName(val) || val.length === 0 || 'Vul een geldige naam in']" />
    <q-input class="margin font-light" outlined v-model="amount" label="Inleg" :rules="[ val => isNumber(val) || val.length === 0 || 'Vul een geldig bedrag in']" />
    <q-input class="margin font-light" outlined v-model="description" label="Beschrijving" />
    <div class="button-container">
      <q-btn class="margin" icon="add" size="5vw" round color="primary" @click="onAdd" />
      <q-btn class="margin" icon="remove" size="5vw" round color="primary" @click="onRemove" />
      <q-btn class="margin" icon="done" size="5vw" round color="primary" @click="onDone" />
      <q-btn class="margin" icon="history" size="5vw" round color="primary" @click="onHistory" />
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'
import { Payment } from '../model/payment'

@Component({
  components: { }
})
export default class InputComponent extends Vue {
  private name = ''
  private amount = ''
  private description = ''
  private isNameCorrect = true
  private isAmountCorrect = true
  private id = 0

  onAdd (): void {
    this.amount = this.amount.replace(/,/g, '.')

    this.isNameCorrect = this.isName(this.name)
    this.isAmountCorrect = this.isNumber(this.amount)

    if (this.isNameCorrect && this.isAmountCorrect) {
      const payment: Payment = {
        id: this.id,
        name: this.name,
        amount: parseFloat(this.amount).toFixed(2),
        description: this.description
      }

      this.id++

      this.$emit('clicked', payment)
      this.reset()
    }
  }

  onRemove (): void {
    this.$emit('clicked', 'remove')
  }

  onDone (): void {
    this.$emit('clicked', 'done')
  }

  onHistory (): void {
    this.$emit('clicked', 'history')
  }

  reset (): void {
    this.name = ''
    this.amount = ''
    this.description = ''
    this.isNameCorrect = true
    this.isAmountCorrect = true
  }

  isName (n: string): boolean {
    return /^[a-zA-Z]+$/.test(n)
  }

  isNumber (n: string): boolean {
    n = n.replace(/,/g, '.')
    return /^-?[\d.]+(?:e-?\d+)?$/.test(n)
  }
}
</script>

<style lang="scss">
.margin {
  margin: 3vw;
}
.button-container {
  display:flex;
  justify-content: center;
}
</style>
