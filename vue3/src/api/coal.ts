import axios from 'axios';
import type { Device, OverviewMetrics, Point, RequirementItem } from '../types/coal';

const http = axios.create({ baseURL: '/api' });

export async function pageDevice(keyword = '', processSection = '', pageNo = 1, pageSize = 10) {
  const { data } = await http.get('/coal/device/page', { params: { keyword, processSection, pageNo, pageSize } });
  return data;
}

export async function createDevice(payload: Device) {
  const { data } = await http.post('/coal/device', payload);
  return data;
}

export async function listPoint(deviceId?: number): Promise<Point[]> {
  const { data } = await http.get('/coal/point/list', { params: { deviceId } });
  return data;
}

export async function issueControl(payload: {
  deviceId: number;
  pointId?: number;
  command: string;
  commandValue?: string;
  operator: string;
  mode: 'MANUAL' | 'AUTO';
  confirmed: boolean;
}) {
  const { data } = await http.post('/coal/control/issue', payload);
  return data;
}

export async function fetchOverviewMetrics(): Promise<OverviewMetrics> {
  const { data } = await http.get('/coal/overview/metrics');
  return data;
}

export async function fetchRequirementList(): Promise<RequirementItem[]> {
  const { data } = await http.get('/coal/overview/requirements');
  return data;
}
