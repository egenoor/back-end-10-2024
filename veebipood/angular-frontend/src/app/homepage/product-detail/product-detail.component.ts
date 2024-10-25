import { Component } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import { Product } from '../../models/Product'
import { ProductService } from '../../services/product.service'

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [],
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.scss'
})
export class ProductDetailComponent {
  product!: Product;

  constructor(private productService: ProductService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get("id")
    if (this.product === null) {
      return;
    }
    this.productService.getProduct(Number(productId)).subscribe(res => this.product = res);
  }
}
