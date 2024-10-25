import { Component, OnInit } from '@angular/core'
import { FormsModule } from '@angular/forms'
import { RouterLink } from '@angular/router'
import { OrderRow } from '../models/OrderRow'
import { Product } from '../models/Product'
import { ProductService } from '../services/product.service'

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent implements OnInit{
  products: Product[] = [];
  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.productService.getProducts().subscribe(response => this.products = response)
  }

  addToCart(productClicked: Product): void {
    const cartLS: OrderRow[] = JSON.parse(localStorage.getItem("cart") || "[]");
    const index = cartLS.findIndex(orderRow => orderRow.product.id === productClicked.id);
    if (index !== -1) {
      // suurendan kogust
      cartLS[index].pcs++;
    } else {
      // pushin > kui pole varasemalt ostukorvis
      cartLS.push({pcs: 1, product: productClicked});
    }
    localStorage.setItem("cart", JSON.stringify(cartLS));
  }
}

