import axios from 'axios';
import type { Device, Point } from '../types/coal';

const http = axios.create({ baseURL: '/api' });

export async function pageDevice(keyword = '', pageNo = 1, pageSize = 10) {
  const { data } = await http.get('/coal/device/page', { params: { keyword, pageNo, pageSize } });
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

export async function issueControl(payload: { deviceId: number; pointId?: number; command: string; commandValue?: string; operator: string }) {
  const { data } = await http.post('/coal/control/issue', payload);
  return data;
}
