<template>
  <div>
    <h2>选煤厂组态能力总览</h2>
    <div style="margin-bottom: 12px;">
      <button @click="loadAll">刷新数据</button>
      <label style="margin-left: 12px;">刷新间隔(ms)
        <input v-model.number="refreshMs" type="number" min="100" step="100" style="width: 100px;" />
      </label>
      <button style="margin-left: 8px;" @click="toggleAuto">{{ timer ? '停止自动刷新' : '开启自动刷新' }}</button>
    </div>

    <div style="display: grid; grid-template-columns: repeat(5, 1fr); gap: 8px; margin-bottom: 16px;">
      <div style="border: 1px solid #ddd; padding: 10px;" v-for="m in metricCards" :key="m.label">
        <div style="color: #666">{{ m.label }}</div>
        <div style="font-size: 24px; font-weight: 700;">{{ m.value }}</div>
      </div>
    </div>

    <table border="1" cellpadding="6" cellspacing="0" width="100%">
      <thead>
      <tr>
        <th>模块</th><th>功能</th><th>能力点</th><th>优先级</th><th>当前状态</th><th>优化建议</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item in requirementList" :key="`${item.module}-${item.feature}`">
        <td>{{ item.module }}</td>
        <td>{{ item.feature }}</td>
        <td>{{ item.capability }}</td>
        <td>{{ item.priority }}</td>
        <td>{{ item.status }}</td>
        <td>{{ item.suggestion }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { computed, onBeforeUnmount, onMounted, ref } from 'vue';
import { fetchOverviewMetrics, fetchRequirementList } from '../../api/coal';
import type { OverviewMetrics, RequirementItem } from '../../types/coal';

const metrics = ref<OverviewMetrics>({ deviceTotal: 0, pointTotal: 0, onlineDevice: 0, alarmToday: 0, controlToday: 0, refreshMinMs: 100 });
const requirementList = ref<RequirementItem[]>([]);
const refreshMs = ref(1000);
let timer: ReturnType<typeof setInterval> | null = null;

const metricCards = computed(() => [
  { label: '设备总数', value: metrics.value.deviceTotal },
  { label: '点位总数', value: metrics.value.pointTotal },
  { label: '在线设备', value: metrics.value.onlineDevice },
  { label: '今日报警', value: metrics.value.alarmToday },
  { label: '今日控制', value: metrics.value.controlToday }
]);

async function loadAll() {
  const [m, r] = await Promise.all([fetchOverviewMetrics(), fetchRequirementList()]);
  metrics.value = m;
  requirementList.value = r;
  if (refreshMs.value < m.refreshMinMs) refreshMs.value = m.refreshMinMs;
}

function toggleAuto() {
  if (timer) {
    clearInterval(timer);
    timer = null;
    return;
  }
  const interval = Math.max(refreshMs.value, metrics.value.refreshMinMs || 100);
  timer = setInterval(loadAll, interval);
}

onMounted(loadAll);
onBeforeUnmount(() => {
  if (timer) clearInterval(timer);
});
</script>
