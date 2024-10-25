import { OrderRow } from './OrderRow'
import { Person } from './Person'

export interface Order {
  id?: number;
  creation?: Date;
  totalSum?: number;
  person: Person;
  orderRows: OrderRow[]
}