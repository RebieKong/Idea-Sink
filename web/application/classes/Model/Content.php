<?php defined('SYSPATH') or die('No direct script access.');

class Model_Content extends ORM {
	protected $_table_name = 't_idea_context';
	protected $_db_group = 'idea';
	protected $_primary_key = 'idea_id';

	public function add_comment($pd)
	{
		if ($pd == 1)
		{
			$this->set('get_times',$this->get_times+1);
		}
		elseif ($pd == 0)
		{
			$this->set('drop_times',$this->drop_times+1);
		}
		$this->save();
	}
}
