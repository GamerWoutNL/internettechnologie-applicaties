<template>
  <q-layout>
    <q-header elevated>
      <q-toolbar>
        <q-toolbar-title class="font-medium header-text">Wie betaald wie</q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>

    <q-footer elevated>
      <q-toolbar />
    </q-footer>
  </q-layout>
</template>

<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'

@Component({
  components: { }
})
export default class MainLayout extends Vue { }
</script>

<style lang="scss">
.header-text {
  font-size: 3vw;
}
</style>
