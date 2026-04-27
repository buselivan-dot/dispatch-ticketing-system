export interface Incident{
  id?: number;
  title: string;
  description: string;
  priority: string;
  status?: string;
  solverId?: number;
}
