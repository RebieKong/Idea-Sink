<?php defined('SYSPATH') or die('No direct script access.');

class Controller_Welcome extends Controller {

	public function action_index()
	{
		$idea = ORM::factory('View_Show')->get_idea();
		print_r($idea->as_array());
		$this->response->body('hello, world!');
	}

	public function action_test()
	{
		$count = Arr::get($_GET,'count',1000);
		//假设的排序比例
		$rate = array(
			1	=> 0.5,
			2 => 0.6,
			3 => 0.7,
			4 => 0.8,
			5 => 0.1,
			6	=> 0.5,
			7 => 0.9,
			8 => 0.3,
			9 => 0.4,
			10 => 0.2,
		);
	 	for ($i=0; $i < $count; $i++){
			$idea = ORM::factory('View_Show')->get_idea();
			$idea_id = $idea->pk();
			$idea_object = ORM::factory('Content',$idea_id);
			$idea_object->set('show_times',$idea_object->show_times+1)->save();
			$pd_rate = $rate[$idea_id];
			$true_rate = mt_rand(0,10)/10;
			$pd = ($true_rate > $pd_rate)?0:1;
			if (mt_rand(0,1000)<400){
				$idea_object->add_comment($pd);
		}
			}
		echo 'ok';
	}

} // End Welcome
