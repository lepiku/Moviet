# Moviet

Aplikasi menampilkan movies dan TV shows dari https://www.themoviedb.org/ .
Project ini dibuat untuk submission "Belajar Android Jetpack Pro" di Dicoding.

## Skenario Pengujian Instrumentation Test

* ui.HomeActivityTest:
  * Menampilkan **List Movies**
    * Memastikan RecyclerView dengan description **MovieListFragment** ditampilkan
    * Memastikan RecyclerView dengan description **TvListFragment** tidak ditampilkan
    * Memastikan RecyclerView **rv_items** descendant dari **MovieListFragment**
    
  * Menampilkan **List TV Shows**
    * Memastikan RecyclerView dengan description **MovieListFragment** tidak ditampilkan
    * Memastikan RecyclerView dengan description **TvListFragment** ditampilkan
    * Memastikan RecyclerView **rv_items** descendant dari **TvListFragment**
    
  * Menampilkan **Detail Movie**
    * Klik item pertama dari RecyclerView **MovieListFragment**
    * Memastikan **img_poster** ditampilkan
    * Memastikan **tv_movie_detail_title** ditampilkan
    * Memastikan **tv_release_date** ditampilkan
    * Memastikan **tv_genre** ditampilkan
    * Memastikan **tv_vote_average** ditampilkan
    * Memastikan **tv_director** ditampilkan
    * Memastikan **tv_overview** ditampilkan
    * Memastikan **tv_status** ditampilkan
    * Memastikan **tv_revenue** ditampilkan
    
  * Menampilkan **Detail TV Show**
    * Klik TabLayout dengan text **TV SHOWS**
    * Klik item pertama dari RecyclerView **TvListFragment**
    * Memastikan **img_poster** ditampilkan
    * Memastikan **tv_tv_detail_name** ditampilkan
    * Memastikan **tv_release_date** ditampilkan
    * Memastikan **tv_genre** ditampilkan
    * Memastikan **tv_vote_average** ditampilkan
    * Memastikan **tv_creator** ditampilkan
    * Memastikan **tv_overview** ditampilkan
    * Memastikan **tv_status** ditampilkan