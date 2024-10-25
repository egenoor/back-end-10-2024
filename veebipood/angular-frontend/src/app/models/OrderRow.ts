import { Product } from './Product'

export interface OrderRow {
  id?: number;
  pcs: number;
  product: Product;
}