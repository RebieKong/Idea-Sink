<?php defined('SYSPATH') or die('No direct script access.');

class Model_View_Show extends ORM {
	protected $_table_name = 'v_idea_show';
	protected $_db_group = 'idea';
	protected $_primary_key = 'idea_id';
	
	public function get_idea()
	{
		$this->limit(1)->find();
		return $this;
	}

} // End Welcome
