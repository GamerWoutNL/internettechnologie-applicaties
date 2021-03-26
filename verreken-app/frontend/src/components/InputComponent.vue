<template>
  <div>
    <q-input class="margin font-light" rounded outlined v-model="name" label="Naam">
      <template v-if="!isNameCorrect" v-slot:append>
        <q-icon name="priority_high" color="red" />
      </template>
    </q-input>
    <q-input class="margin font-light" rounded outlined v-model="amount" label="Inleg">
      <template v-if="!isAmountCorrect" v-slot:append>
        <q-icon name="priority_high" color="red" />
      </template>
    </q-input>
    <q-input class="margin font-light" rounded outlined v-model="description" label="Beschrijving" />
    <q-btn class="margin" icon="add" size="5vw" round color="primary" @click="onAdd" />
    <q-btn class="margin" icon="remove" size="5vw" round color="primary" @click="onRemove" />
    <q-btn class="margin" icon="done" size="5vw" round color="primary" @click="onDone">
      <q-popup-proxy>
        <q-banner>
          <template v-slot:avatar>
            <q-icon name="error" color="primary" />
          </template>
          Je hebt nog geen verrekeningen om op te slaan.
        </q-banner>
      </q-popup-proxy>
    </q-btn>
    <q-btn class="margin" icon="history" size="5vw" round color="primary" @click="onHistory">
      <q-popup-proxy>
        <q-banner>
          <template v-slot:avatar>
            <q-icon name="error" color="primary" />
          </template>
          Je hebt nog inleggingen staan. Sla ze eerst op of verwijder ze.
        </q-banner>
      </q-popup-proxy>
    </q-btn>
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
