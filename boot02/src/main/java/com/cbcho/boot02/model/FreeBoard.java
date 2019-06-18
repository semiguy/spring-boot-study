package com.cbcho.boot02.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "replies")
@Entity
@Table(name = "tbl_freeboards")
@EqualsAndHashCode(of = "bno")
public class FreeBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bno;
	private String title;
	private String writer;
	private String content;
	
	@CreationTimestamp
	private Timestamp regdate;
	@CreationTimestamp
	private Timestamp updatedate;
	
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<FreeBoardReply> replies;
}
