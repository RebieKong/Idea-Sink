<?php defined('SYSPATH') or die('No direct script access.');

class Controller_Index extends Controller {

	public function action_index()
	{
		$idea = ORM::factory('View_Show')->get_idea();
		$idd = ORM::factory('Content',$idea->pk());
		$nickname = Cookie::get('nickname','');
		if ($idd->loaded()) {
			$idd->set('show_times',$idd->show_times+1)->save();
		}
		$this->assign('idea',$idea);
		$this->assign('nickname',$nickname);
		$this->display('Show/Index2');
	}

} // End Welcome
