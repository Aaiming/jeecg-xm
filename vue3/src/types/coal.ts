export interface Device {
  id?: number;
  deviceCode: string;
  deviceName: string;
  deviceType: string;
  processSection: string;
  protocolType: string;
  enabled?: boolean;
}

export interface Point {
  id?: number;
  pointCode: string;
  deviceId: number;
  pointName: string;
  dataType: string;
  unit?: string;
  rwType: string;
  addr: string;
  collectCycleMs: number;
}

export interface RequirementItem {
  module: string;
  feature: string;
  capability: string;
  priority: 'P0' | 'P1' | 'P2';
  status: '已完成' | '部分完成' | '规划中';
  suggestion: string;
}

export interface OverviewMetrics {
  deviceTotal: number;
  pointTotal: number;
  onlineDevice: number;
  alarmToday: number;
  controlToday: number;
  refreshMinMs: number;
}
