import { Category } from './Category'
import { Nutrients } from './Nutrients'

export interface Product {
  id: number; // id-d Long
  name: string;
  price: number;
  image: string;
  active: boolean;
  description: string;
  nutrients: Nutrients;
  category: Category
}