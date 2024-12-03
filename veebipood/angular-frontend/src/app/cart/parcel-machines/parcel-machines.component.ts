import { Component } from '@angular/core';
import { ParcelMachineService } from '../../services/parcel-machine.service'

@Component({
  selector: 'app-parcel-machines',
  standalone: true,
  imports: [],
  templateUrl: './parcel-machines.component.html',
  styleUrl: './parcel-machines.component.scss'
})
export class ParcelMachinesComponent {
  parcelMachines: any[] = [];

  constructor(private parcelMachineService: ParcelMachineService) {}

  ngOnInit(): void {
    this.parcelMachineService.getParcelMachines().subscribe(res => {
      this.parcelMachines = res;
    })
  }

  getPMsByCountry(country: string) {
    this.parcelMachineService.getParcelMachinesByCountry(country).subscribe(res => {
      this.parcelMachines = res;
    })
  }
}
