import { Address } from './Address'

export class Person {
  constructor(public email: string,
    public password?: string,
    public firstName?: string,
    public lastName?: string,
    public address?: Address
  ){}
}