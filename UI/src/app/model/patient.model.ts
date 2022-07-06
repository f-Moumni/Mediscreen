import {Gender} from "../enum/Gender.enum";

export class Patient {
  id?: number;
  firstName!: string;
  lastName!: string;
  birthdate!: Date;
  gender!: Gender;
  address?: string;
  phone?: string;
}
