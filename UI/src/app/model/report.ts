import {Gender} from "../enum/Gender.enum";

export class Report {
  id?: number;
  name!: string;
  age!: number;
  riskLevel!: string;
  gender!: Gender;
  address?: string;
  phone?: string;

}
