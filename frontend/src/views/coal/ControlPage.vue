<template>
  <div>
    <h2>远程控制</h2>
    <div>
      <label>设备ID: <input v-model.number="form.deviceId" type="number"/></label>
      <label>点位ID: <input v-model.number="form.pointId" type="number"/></label>
      <label>命令: <input v-model="form.command"/></label>
      <label>值: <input v-model="form.commandValue"/></label>
      <label>操作人: <input v-model="form.operator"/></label>
      <button @click="submit">下发</button>
    </div>
    <p>{{ result }}</p>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { issueControl } from '../../api/coal';

const form = ref({ deviceId: 1, pointId: undefined as number | undefined, command: 'START', commandValue: '1', operator: 'admin' });
const result = ref('');

async function submit() {
  const res = await issueControl(form.value);
  result.value = JSON.stringify(res);
}
</script>
