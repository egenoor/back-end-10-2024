import { Component } from '@angular/core'
import { SupplierService } from '../../services/supplier.service'

@Component({
  selector: 'app-supplier',
  standalone: true,
  imports: [],
  templateUrl: './supplier.component.html',
  styleUrl: './supplier.component.scss'
})
export class SupplierComponent {
  supplierProducts: any[] = [];

  constructor(private supplierService: SupplierService){}

  ngOnInit(): void {
    this.supplierService.getSupplierProducts().subscribe(res => {
      this.supplierProducts = res;
    })
  }

}
