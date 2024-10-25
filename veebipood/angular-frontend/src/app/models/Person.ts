import { Address } from './Address'

export interface Person {
  email: string;
  firstName?: string;
  lastName?: string;
  address?: Address;
}