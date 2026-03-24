<template>
  <div>
    <h2>设备台账</h2>
    <div style="margin-bottom: 10px; display: flex; gap: 8px; align-items: center;">
      <input v-model="keyword" placeholder="设备名称/编码" />
      <select v-model="processSection">
        <option value="">全部工艺段</option>
        <option v-for="s in sections" :key="s" :value="s">{{ s }}</option>
      </select>
      <button @click="load">查询</button>
    </div>
    <table border="1" cellpadding="6">
      <thead><tr><th>ID</th><th>编码</th><th>名称</th><th>类型</th><th>工艺段</th><th>协议</th><th>状态</th></tr></thead>
      <tbody>
      <tr v-for="d in records" :key="d.id">
        <td>{{ d.id }}</td><td>{{ d.deviceCode }}</td><td>{{ d.deviceName }}</td><td>{{ d.deviceType }}</td><td>{{ d.processSection }}</td><td>{{ d.protocolType }}</td><td>{{ d.enabled ? '在线' : '离线' }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { pageDevice } from '../../api/coal';
import type { Device } from '../../types/coal';

const keyword = ref('');
const processSection = ref('');
const sections = ['RAW_COAL', 'HEAVY_MEDIA', 'FLOTATION', 'DEWATERING', 'SLURRY_WATER', 'STORAGE_TRANSPORT', 'AUXILIARY'];
const records = ref<Device[]>([]);

async function load() {
  const res = await pageDevice(keyword.value, processSection.value, 1, 20);
  records.value = res.records || [];
}

onMounted(load);
</script>
