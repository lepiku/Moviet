# Moviet

Aplikasi menampilkan movies dan TV shows dari https://www.themoviedb.org/ .
Project ini dibuat untuk submission "Belajar Android Jetpack Pro" di Dicoding.

## Skenario Pengujian Instrumentation Test

* ui.HomeActivityTest:
  * Menampilkan **Detail Movie**
    * Memastikan rv_item ditampilkan
    * Klik item pertama dari RecyclerView pada list Movie
    * Memastikan img_poster ditampilkan
    * Memastikan tv_movie_detail_title ditampilkan
    * Memastikan tv_release_date ditampilkan
    * Memastikan tv_genre ditampilkan
    * Memastikan tv_vote_average ditampilkan
    * Memastikan tv_director ditampilkan
    * Memastikan tv_overview ditampilkan
    * Memastikan tv_status ditampilkan
    * Memastikan tv_revenue ditampilkan
    
  * Menampilkan **Detail TV Show**
    * Memastikan rv_item ditampilkan
    * Klik TabLayout dengan text "TV SHOWS"
    * Klik item pertama dari RecyclerView pada list TV show
    * Memastikan img_poster ditampilkan
    * Memastikan tv_tv_detail_name ditampilkan
    * Memastikan tv_release_date ditampilkan
    * Memastikan tv_genre ditampilkan
    * Memastikan tv_vote_average ditampilkan
    * Memastikan tv_creator ditampilkan
    * Memastikan tv_overview ditampilkan
    * Memastikan tv_status ditampilkan