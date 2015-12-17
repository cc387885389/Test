<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace必须指向Dao接口 -->
<mapper namespace="${info.name}Dao">
    
    <resultMap id="${info.name}Map" type="${info.name}">
		<result column="id" property="id" />
	</resultMap>
    
    
    <sql id="selectInfo">
		${sqlInfo}
	</sql>
	
	<sql id="searchWhere">
		<where>
			
		</where>
	</sql>


	<select id="searchTotal" parameterType="map" resultType="long">
          select count(1)
          FROM ${tableName}
          <include refid="searchWhere"/>
    </select>
    
	<select id="search" parameterType="map" resultMap="${info.name}Map">
		select 
		<include refid="selectInfo" />
		FROM ${tableName} ${alias}
		<include refid="searchWhere"/>
		<if test="sort != null">
            order by
            <foreach collection="sort" item="order" separator=",">
               ${r"${order.property}"}   ${r"${order.direction}"}
            </foreach>
        </if>
        limit ${r"#{offset}"}, ${r"#{pageSize}"}
	</select>
</mapper>