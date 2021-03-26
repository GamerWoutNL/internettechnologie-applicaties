<template>
  <q-page>
    <div>GESCHIEDENIS</div>
  </q-page>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'

@Component({
  components: { }
})
export default class HistoryPage extends Vue {
  redirect (path: string): void {
    this.$router.push({ path: path }).catch((error) => {
      console.log(error)
    })
  }
}
</script>
