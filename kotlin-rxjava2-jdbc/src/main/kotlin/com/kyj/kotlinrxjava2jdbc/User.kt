package com.kyj.kotlinrxjava2jdbc

import org.davidmoten.rx.jdbc.annotations.Column

interface User {
  @Column
  fun name(): String
}