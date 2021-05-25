# Moviet

Aplikasi menampilkan movies dan TV shows dari https://www.themoviedb.org/ .
Project ini dibuat untuk submission
[Belajar Android Jetpack Pro](https://www.dicoding.com/academies/129) dan
[Menjadi Android Developer Expert](https://www.dicoding.com/academies/165) di Dicoding.

## Version History

* Belajar Android Jetpack Pro
  * [Submission 1](https://github.com/lepiku/Moviet/tree/v1): Fri May 7 2021
  * [Submission 2](https://github.com/lepiku/Moviet/tree/v2): Mon May 10 2021
  * [Submission 3](https://github.com/lepiku/Moviet/tree/v3): Sun May 19 2021
* Menjadi Android Developer Expert
  * [Capstone](https://github.com/lepiku/Moviet/tree/capstone): Tue May 25 2021

## Skenario Pengujian Instrumentation Test

* ui.HomeActivityTest:
  * Menampilkan **List Movies**
    * Memastikan RecyclerView dengan description **MovieListFragment** ditampilkan
    * Memastikan RecyclerView dengan description **TvListFragment** tidak ditampilkan
    * Memastikan RecyclerView **rv_items** descendant dari **MovieListFragment**
  * Menampilkan **List TV Shows**
    * Klik TabLayout dengan text **TV SHOWS**
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
* ui.FavoriteActivityTest
  * Favorite dan un-favorite **Movies**
    * Klik item pertama pada list untuk masuk ke **Detail Movie**
    * Klik tombol **Favorite** untuk **menambah** movie ke favorites
    * Kembali ke halaman **List Movies**
    * Klik tombol **Menu Favorite** untuk masuk ke halaman **Favorites**
    * Memastikan ada **1 item** pada **List Favorite Movies**
    * Klik item pertama pada list untuk masuk ke **Detail Movie**
    * Klik tombol **Favorite** untuk **menghapus** movie dari favorites
    * Kembali ke halaman **List Favorite Movies**
    * Memastikan ada **0 item** pada **List Favorite Movies**
  * Favorite dan un-favorite **TV Shows**
    * Klik TabLayout dengan text **TV SHOWS**
    * Klik item pertama pada list untuk masuk ke **Detail TV Show**
    * Klik tombol **Favorite** untuk **menambah** TV show ke favorites
    * Kembali ke halaman **List TV Shows**
    * Klik tombol **Menu Favorite** untuk masuk ke halaman **Favorites**
    * Klik TabLayout dengan text **TV SHOWS**
    * Memastikan ada **1 item** pada **List Favorite TV Shows**
    * Klik item pertama pada list untuk masuk ke **Detail TV Show**
    * Klik tombol **Favorite** untuk **menghapus** TV show dari favorites
    * Kembali ke halaman **List Favorite TV Shows**
    * Memastikan ada **0 item** pada **List Favorite TV Shows**
