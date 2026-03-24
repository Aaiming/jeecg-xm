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
