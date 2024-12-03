import { Component } from '@angular/core'
import { FormsModule, NgForm } from '@angular/forms'
import { AutosizeDirective } from '../../directives/autosize.directive'
import { Category } from '../../models/Category'
import { Nutrients } from '../../models/Nutrients'
import { Product } from '../../models/Product'
import { ProductService } from '../../services/product.service'

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [FormsModule, AutosizeDirective],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.scss'
})
export class AddProductComponent {
  message: string = "";

  constructor(private productService: ProductService) {}
  addProduct(form: NgForm) {
    const val = form.value;
    const product = new Product(
      val.id,
      val.name,
      val.price,
      val.image,
      val.active,
      val.description,
      new Nutrients(
        val.carbohydrate,
        val.protein,
        val.fat
      ),
      new Category(
        val.category,
        ""
      )
    )
    this.productService.addProduct(product).subscribe(() => {},
    error => {
      this.message = error.error.name;
    }
  );
  }
}
