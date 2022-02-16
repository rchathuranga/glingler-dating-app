import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-explorer',
  templateUrl: './explorer.component.html',
  styleUrls: ['./explorer.component.css']
})
export class ExplorerComponent implements OnInit {
  posts: number[] = new Array(5);
  imageFiles: File[] = [];
  imageUrls: any[] = [];
  myPostTitle = '';

  constructor() {
  }

  ngOnInit(): void {
  }

  loadMore() {
    this.posts = new Array(this.posts.length + 5);
  }

  fileInputChangeEvent($event: Event) {
    const target: any = event.target;
    for (const tFiles of target.files) {
      const file: File = tFiles;
      this.imageFiles.push(file);

      console.log(this.imageFiles);

      const reader = new FileReader();
      reader.onload = (e) => {
        this.imageUrls.push(e.target.result);
      };
      reader.readAsDataURL(file);

      console.log(this.imageUrls);
    }
  }
}
