- Application deployment process:  
  Application Binary -> Cell (VM) -> Staging using Buildpack (Compile and run)) -> Droplet (Docker) -> Run on multiple Cells

- Cell sizing
  - Too small sizing wastes the resource due to OS headroom.
  - Too large sizing increases the risk when cell failed.
  - Recommendation: 32G for 512M app, 64G for 2G app
