import { Category } from './Category'
import { Nutrients } from './Nutrients'

export class Product {
  constructor(
  public id: number, // id-d Long
  public name: string,
  public price: number,
  public image: string,
  public active: boolean,
  public description: string,
  public nutrients: Nutrients,
  public category: Category
  ) {}
}