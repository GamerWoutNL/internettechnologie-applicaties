<template>
  <div>
    <div v-for="user in users" :key="user.name">
      {{user}}
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Prop, Component } from 'vue-property-decorator'
import { User } from '../model/user'

@Component({
  components: { }
})
export default class ShowComponent extends Vue {
  @Prop() readonly users: User[]
}
</script>

<style lang="scss">
.margin {
  margin: 3vw;
}
</style>
