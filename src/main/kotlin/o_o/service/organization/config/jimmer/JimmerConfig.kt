package o_o.service.organization.config.jimmer

import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.newKSqlClient
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.DataSourceUtils
import javax.sql.DataSource

@Bean
fun sqlClient(dataSource: DataSource): KSqlClient =
  newKSqlClient {
    setConnectionManager {
      val con = DataSourceUtils
        .getConnection(dataSource)
      try {
        proceed(con)
      } finally {
        DataSourceUtils
          .releaseConnection(con, dataSource)
      }
    }
  }